SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[prenda_tienda](
	[prenda_id] [int] NOT NULL,
	[tienda_id] [int] NOT NULL,
	[cantidad] [int] NULL
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[prenda_tienda]  WITH CHECK ADD  CONSTRAINT [FK__prenda_tienda_1] FOREIGN KEY([tienda_id])
REFERENCES [dbo].[tiendas] ([id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[prenda_tienda] CHECK CONSTRAINT [FK__prenda_tienda_1]
GO
ALTER TABLE [dbo].[prenda_tienda]  WITH CHECK ADD  CONSTRAINT [FK__prenda_tienda_2] FOREIGN KEY([prenda_id])
REFERENCES [dbo].[prendas] ([id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[prenda_tienda] CHECK CONSTRAINT [FK__prenda_tienda_2]
GO
