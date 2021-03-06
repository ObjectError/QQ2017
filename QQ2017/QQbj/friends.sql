USE [MyQQ]
GO
/****** 对象:  Table [dbo].[Friends]    脚本日期: 01/03/2017 19:21:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Friends](
	[friendId] [int] IDENTITY(1,1) NOT NULL,
	[myQQcode] [int] NOT NULL,
	[friendQQcode] [int] NOT NULL,
	[groupName] [varchar](20) COLLATE Chinese_PRC_CI_AS NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[friendId] ASC
)WITH (IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF