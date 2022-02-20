SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_check_item_shop]
(
    @ITEM_ID INT
	,@SHOP_ID INT

)
AS
BEGIN
    
    SET NOCOUNT ON

	SELECT cantidad
	FROM prenda_tienda
	WHERE prenda_id = @ITEM_ID
		AND tienda_id = @SHOP_ID

END
GO
