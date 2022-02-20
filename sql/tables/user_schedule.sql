SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[user_schedule](
	[user_id] [int] NOT NULL,
	[schedule_id] [int] NOT NULL,
	[weekday] [char](1) NOT NULL
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[user_schedule]  WITH CHECK ADD  CONSTRAINT [FK__user_sche__sched__236943A5] FOREIGN KEY([schedule_id])
REFERENCES [dbo].[schedules] ([id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[user_schedule] CHECK CONSTRAINT [FK__user_sche__sched__236943A5]
GO
ALTER TABLE [dbo].[user_schedule]  WITH CHECK ADD  CONSTRAINT [FK_user_schedule_users] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[user_schedule] CHECK CONSTRAINT [FK_user_schedule_users]
GO
