SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_search_item]
(
	@BAR_CODE VARCHAR(10)
)
AS
BEGIN
    
    SET NOCOUNT ON

	SELECT p.id
		,p.nombre
		,p.CB
		,p.tipo_id
		,t.nombre 'nombre_tipo'
		,p.talla
		,p.precio
	FROM prendas p
		INNER JOIN tipo_prenda t
			ON p.tipo_id = t.id
	WHERE CB = @BAR_CODE

END
GO
