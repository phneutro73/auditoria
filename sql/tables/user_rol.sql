SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[user_rol](
	[user_id] [int] NULL,
	[role_id] [int] NULL
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[user_rol]  WITH CHECK ADD FOREIGN KEY([role_id])
REFERENCES [dbo].[roles] ([id])
GO
ALTER TABLE [dbo].[user_rol]  WITH CHECK ADD  CONSTRAINT [FK__user_rol__user_i__02FC7413] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[user_rol] CHECK CONSTRAINT [FK__user_rol__user_i__02FC7413]
GO
