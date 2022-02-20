SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_delete_item]
(
	@ID INT
)
AS
BEGIN
    
    SET NOCOUNT ON

	DELETE
	FROM prendas
	WHERE id = @ID

	DELETE
	FROM prenda_tienda
	WHERE prenda_id = @ID

END
GO
