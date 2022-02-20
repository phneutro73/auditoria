SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[reservas](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[id_tienda] [int] NOT NULL,
	[id_prenda] [int] NOT NULL,
	[cantidad] [int] NOT NULL,
	[reserva_timestamp] [timestamp] NOT NULL,
	[dni] [varchar](9) NULL,
	[correo] [varchar](60) NULL,
	[fin_reserva_datetime] [datetime] NULL
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[reservas] ADD  CONSTRAINT [PK__reservas__3213E83F6D7CE289] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
