SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[user_tienda](
	[user_id] [int] NOT NULL,
	[tienda_id] [int] NOT NULL
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[user_tienda]  WITH CHECK ADD  CONSTRAINT [FK__user_tienda_1] FOREIGN KEY([tienda_id])
REFERENCES [dbo].[tiendas] ([id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[user_tienda] CHECK CONSTRAINT [FK__user_tienda_1]
GO
ALTER TABLE [dbo].[user_tienda]  WITH CHECK ADD  CONSTRAINT [FK__user_tienda_2] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[user_tienda] CHECK CONSTRAINT [FK__user_tienda_2]
GO
