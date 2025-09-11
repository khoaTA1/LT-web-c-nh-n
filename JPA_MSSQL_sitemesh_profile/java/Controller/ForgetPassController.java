package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Random;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import Service.UserService;
import Impl.UserServiceImpl;

@WebServlet(urlPatterns = "/forgetpass")
public class ForgetPassController extends HttpServlet {

	@SuppressWarnings("static-access")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String otp_code = "";
		
		UserService user = new UserServiceImpl();
		
		if (user.checkExistEmail(email)) {
			// Tạo mã OTP 6 số gửi về mail nếu mail đã đăng ký
			otp_code = this.generateOTP(6);
			
			HttpSession ss = req.getSession();
			ss.setAttribute("otp-email", email);
			ss.setAttribute("otp", otp_code);
			ss.setAttribute("otp-timeout", System.currentTimeMillis());
			
			this.sendEmail(email, "OTP thay đổi mật khẩu", otp_code);
			
			resp.sendRedirect("/views/general/enterotp.jsp");
		} else {
			PrintWriter out = resp.getWriter();
			out.print("Email chua duoc dang ky!");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ss = req.getSession();
		String alertmsg = "";
		
		// Kiểm tra xem nếu thời gian tồn tại của mã otp có quá 3 phút không
		if ((long)ss.getAttribute("otp-timeout") - System.currentTimeMillis() <= 3*60*1000) {
			String otp_code_entered = req.getParameter("otp");
			
			if (ss.getAttribute("otp").equals(otp_code_entered)) {
				resp.sendRedirect("/KN_CSDL/wp_t1/changepass.jsp");
			} else {
				alertmsg = "Sai mã OTP!";
				req.setAttribute("alert", alertmsg);
				req.getRequestDispatcher("/wp_t1/enterotp.jsp").forward(req, resp);
				return;
			}
		} else {
			alertmsg = "Mã OTP đã hết hạn! Vui lòng yêu cầu lại mã OTP";
			req.setAttribute("alert", alertmsg);
			req.getRequestDispatcher("/wp_t1/enterotp.jsp").forward(req, resp);
			return;
		}
	}
	
	// Hàm tạo OTP
	public static String generateOTP(int length) {
        String scope = "0123456789";
        Random rand = new Random();
        StringBuilder otp = new StringBuilder();

        for (int i = 0; i < length; i++) {
            otp.append(scope.charAt(rand.nextInt(scope.length())));
        }

        return otp.toString();
    }
	
	// Hàm gửi mail
	public static void sendEmail(String to, String subject, String content) {
        final String username = "spit.dept.n1@gmail.com";
        final String password = "vqnbpedvxylmpzph";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

		Session ss = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

        try {
            Message message = new MimeMessage(ss);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);
            System.out.println("Email đã được gửi đến: " + to);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
