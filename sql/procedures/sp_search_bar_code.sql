SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_search_bar_code]
(
	@BAR_CODE VARCHAR(10)
)
AS
BEGIN
    
    SET NOCOUNT ON

	SELECT nombre
		,CB
		,tipo_id
		,talla
		,precio
		,COUNT(CB) 'cantidad'
	FROM prendas
	WHERE CB = @BAR_CODE
	GROUP BY CB, nombre, tipo_id, talla, precio

END
GO
