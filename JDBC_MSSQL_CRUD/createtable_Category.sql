create table Category (
	cate_id int identity(1,1) not null,
	cate_name nvarchar(255) not null,
	icons nvarchar(255) null,
	PRIMARY KEY CLUSTERED (cate_id ASC) WITH (PAD_INDEX = OFF, 
											  STATISTICS_NORECOMPUTE = OFF, 
											  IGNORE_DUP_KEY = OFF, 
											  ALLOW_ROW_LOCKS = ON,
											  ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]