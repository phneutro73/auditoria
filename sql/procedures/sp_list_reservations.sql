SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_list_reservations]
(
	@IS_HISTORIC BIT
)
AS
BEGIN
    
    SET NOCOUNT ON

	IF @IS_HISTORIC = 1
		BEGIN
			SELECT 
				r.id
				,r.correo
				,r.dni
				,t.nombre_tienda
				,p.CB
				,r.cantidad
				,p.precio
				,r.fin_reserva_datetime
			FROM reservas r
				LEFT JOIN prendas p
					ON r.id_prenda = p.id
				LEFT JOIN tiendas t
					ON r.id_tienda = t.id
			WHERE r.fin_reserva_datetime < GETDATE()
			END
		ELSE
			BEGIN
				SELECT 
					r.id
					,r.correo
					,r.dni
					,t.nombre_tienda
					,p.CB
					,r.cantidad
					,p.precio
					,r.fin_reserva_datetime
				FROM reservas r
					LEFT JOIN prendas p
						ON r.id_prenda = p.id
					LEFT JOIN tiendas t
						ON r.id_tienda = t.id
				WHERE r.fin_reserva_datetime > GETDATE()
			END

END
GO