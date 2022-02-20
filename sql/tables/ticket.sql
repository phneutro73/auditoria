SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ticket](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[id_prenda] [int] NOT NULL,
	[id_usuario] [int] NOT NULL,
	[cantidad] [int] NOT NULL,
	[venta_timestamp] [timestamp] NOT NULL,
	[venta_datetime] [datetime] NULL
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[ticket] ADD PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
