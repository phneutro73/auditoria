SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_create_reservation]
(
    @SHOP_ID INT
	,@ITEM_ID INT
	,@ID_NUMBER VARCHAR(20)
	,@EMAIL VARCHAR(255)

)
AS
BEGIN
    
    SET NOCOUNT ON

	INSERT INTO reservas
	VALUES
		(@SHOP_ID
		,@ITEM_ID
		,1
		,DEFAULT
		,@ID_NUMBER
		,@EMAIL
		,DATEADD(minute,20,GETDATE())
		)

END
GO
