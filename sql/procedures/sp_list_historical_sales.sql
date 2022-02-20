SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_list_historical_sales]

AS
BEGIN
    
    SET NOCOUNT ON
	
	SELECT v.id_prenda
		,CASE WHEN p.CB IS NULL THEN '-' ELSE p.CB END 'CB'
		,CASE WHEN l.user_name IS NULL THEN '-' ELSE l.user_name END 'user_name'
		,CASE WHEN t.nombre_tienda IS NULL THEN '-' ELSE t.nombre_tienda END 'nombre_tienda'
		,CASE WHEN p.precio IS NULL THEN 0 ELSE p.precio END 'precio'
		,v.venta_datetime 
	FROM ventas v
		LEFT JOIN logings l
			ON v.id_usuario = l.user_id
		LEFT JOIN prendas p
			ON v.id_prenda = p.id
		LEFT JOIN user_tienda ut
			ON l.user_id = ut.user_id
		LEFT JOIN tiendas t
			ON ut.tienda_id = t.id

END
GO
