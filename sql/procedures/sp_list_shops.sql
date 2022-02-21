SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_list_shops]

AS
BEGIN
    
    SET NOCOUNT ON

	SELECT t.id,
		t.nombre_tienda,
		t.direccion,
		COUNT(ut.user_id) 'count_users_shop'
	FROM tiendas t
		LEFT JOIN user_tienda ut
			ON t.id = ut.tienda_id
	GROUP BY t.id, t.nombre_tienda, t.direccion

END
GO
