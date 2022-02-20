SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_list_item_detail]
(
	@ID INT
	,@SHOP_ID INT
)
AS 
BEGIN
    
    SET NOCOUNT ON

		SELECT p.id 'id'
		,p.cb 'cb'
		,CASE WHEN p.nombre IS NULL THEN '-' ELSE p.nombre END 'nombre'
		,CASE WHEN tip_p.nombre IS NULL THEN '-' ELSE tip_p.nombre END 'tipo_id'
		,CASE WHEN p.talla IS NULL THEN '-' ELSE p.talla END 'talla'
		,SUM(p_tien.cantidad) 'cantidad_total'
		,CASE WHEN cant_shop.cantidad IS NULL THEN 0 ELSE cant_shop.cantidad END 'cantidad_en_tienda'
		,CASE WHEN r.cantidad IS NULL THEN 0 ELSE R.cantidad END 'cantidad_reservas'
		,p.precio 'precio'
	FROM prendas p
		INNER JOIN prenda_tienda p_tien
			ON p.id = p_tien.prenda_id
		LEFT JOIN prenda_tienda cant_shop
			ON p_tien.prenda_id = cant_shop.prenda_id
				AND cant_shop.tienda_id = @SHOP_ID
		LEFT JOIN reservas r
			ON p.id = r.id_prenda
				AND r.fin_reserva_datetime >= GETDATE()
		LEFT JOIN tipo_prenda tip_p
			ON p.tipo_id = tip_p.id 
	WHERE p.id = @ID
	GROUP BY p.id
	,p.cb
	,p.nombre
	,tip_p.nombre
	,p.talla
	,p.precio
	,cant_shop.cantidad
	,r.cantidad

END
GO
