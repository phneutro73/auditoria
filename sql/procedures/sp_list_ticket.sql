SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_list_ticket]
(
	@ID_USER INT
)
AS
BEGIN
    
    SET NOCOUNT ON

	SELECT t.id_prenda
		,p.CB
		,p.nombre
		,tp.nombre 'nombre_tipo'
		,p.talla
		,p.precio
	FROM ticket t
		INNER JOIN prendas p
			ON t.id_prenda = p.id 
		INNER JOIN tipo_prenda tp
			ON tp.id = p.tipo_id
	WHERE t.id_usuario = @ID_USER

END
GO
