SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_update_item_quantity]
(
	@ITEM_ID INT
	,@QUANTITY INT
	,@SHOP_ID INT
)
AS
BEGIN
    
    SET NOCOUNT ON

	IF EXISTS (SELECT *
			FROM prenda_tienda
			WHERE tienda_id = @SHOP_ID
				AND prenda_id = @ITEM_ID)
		BEGIN

			DECLARE @act_qnt INT = (SELECT cantidad 
									FROM prenda_tienda 
									WHERE tienda_id = @SHOP_ID
										AND prenda_id = @ITEM_ID)
			
			UPDATE prenda_tienda
			SET cantidad = @act_qnt + @QUANTITY
			FROM prenda_tienda
				WHERE tienda_id = @SHOP_ID
					AND prenda_id = @ITEM_ID

		END
	ELSE
		BEGIN

			INSERT INTO prenda_tienda
			VALUES 
				(@item_id
				,@SHOP_ID
				,@QUANTITY
				)

		END

END
GO
