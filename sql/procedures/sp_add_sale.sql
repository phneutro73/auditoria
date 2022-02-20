SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_add_sale]
(
    @ITEM_ID INT
	,@USER_ID INT
	,@QUANTITY INT
	,@SHOP_ID INT

)
AS
BEGIN
    
    SET NOCOUNT ON

	INSERT INTO ventas
	VALUES
		(@ITEM_ID
		,@USER_ID
		,@QUANTITY
		,DEFAULT
		,GETDATE())

	DELETE 
	FROM ticket
		WHERE id_prenda = @ITEM_ID
			AND id_usuario = @USER_ID

	DECLARE @cantidad INT
	SET @cantidad = (SELECT cantidad
				FROM prenda_tienda
				WHERE tienda_id = @SHOP_ID
					AND prenda_id = @ITEM_ID)
					
	UPDATE prenda_tienda
	SET cantidad = @cantidad - 1
	FROM prenda_tienda
	WHERE tienda_id = @SHOP_ID
		AND prenda_id = @ITEM_ID

END
GO
