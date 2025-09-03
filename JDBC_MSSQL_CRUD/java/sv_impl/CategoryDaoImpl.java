package sv_impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import KN.KN_SQLSV;
import Model.Category;
import service.CategoryDao;

public class CategoryDaoImpl extends KN_SQLSV implements CategoryDao {
	
	@Override
	public void insert(Category category) {
		String sql_qu = "INSERT INTO [Category](cate_name,icons) VALUES(?,?);";
		
		try {
			Connection conn = super.getConnection();
			PreparedStatement prs = conn.prepareStatement(sql_qu);
			prs.setString(1, category.getCatename());
			prs.setString(2, category.getIcon());
			prs.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void edit(Category category) {
		String sql_qu = "UPDATE [Category] SET cate_name = ?,icons = ? WHERE cate_id = ?;";
		
		try {
			Connection conn = super.getConnection();
			PreparedStatement prs = conn.prepareStatement(sql_qu);
			
			prs.setString(1, category.getCatename());
			prs.setString(2, category.getIcon());
			prs.setInt(3, category.getCateid());
			prs.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void delete(int id) {
		String sql_qu = "DELETE FROM [Category] WHERE cate_id = ?";
		
		try {
			Connection conn = super.getConnection();
			PreparedStatement prs = conn.prepareStatement(sql_qu);
			
			prs.setInt(1, id);
			prs.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Category get(int id) {
		String sql_qu = "SELECT * FROM [Category] WHERE cate_id = ?";
		
		try {
			Connection conn = super.getConnection();
			PreparedStatement prs = conn.prepareStatement(sql_qu);
			
			prs.setInt(1, id);
			ResultSet rs = prs.executeQuery();
			
			Category category = new Category();
			
			if (rs.next()) {
				category.setCateid(rs.getInt("cate_id"));
				category.setCatename(rs.getString("cate_name"));
				category.setIcon(rs.getString("icons"));
			}
			return category;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Category get(String name) {
		String sql_qu = "SELECT * FROM [Category] WHERE cate_name = ?";
		
		try {
			Connection conn = super.getConnection();
			PreparedStatement prs = conn.prepareStatement(sql_qu);
			
			prs.setString(1, name);
			ResultSet rs = prs.executeQuery();
			
			Category category = new Category();
			
			if (rs.next()) {
				category.setCateid(rs.getInt("cate_id"));
				category.setCatename(rs.getString("cate_name"));
				category.setIcon(rs.getString("icons"));
			}
			return category;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Category> getAll() {
		String sql_qu = "SELECT * FROM [Category]";
		
		try {
			Connection conn = super.getConnection();
			PreparedStatement prs = conn.prepareStatement(sql_qu);
			ResultSet rs = prs.executeQuery();
			
			List<Category> list = new ArrayList<>();
			
			while (rs.next()) {
				Category category = new Category();
				
				category.setCateid(rs.getInt("cate_id"));
				category.setCatename(rs.getString("cate_name"));
				category.setIcon(rs.getString("icons"));
				
				list.add(category);
			}
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public List<Category> search(String keyword) {
		String sql_qu = "SELECT * FROM [Category] WHERE cate_name = ?";
		
		try {
			Connection conn = super.getConnection();
			PreparedStatement prs = conn.prepareStatement(sql_qu);
			
			prs.setString(1, keyword);
			ResultSet rs = prs.executeQuery();
			
			List<Category> list = new ArrayList<>();
			
			while (rs.next()) {
				Category category = new Category();
				
				category.setCateid(rs.getInt("cate_id"));
				category.setCatename(rs.getString("cate_name"));
				category.setIcon(rs.getString("icons"));
				
				list.add(category);
			}
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
