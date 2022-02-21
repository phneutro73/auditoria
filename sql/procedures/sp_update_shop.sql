SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_update_shop]
(
	@ID INT
    ,@NOMBRE_TIENDA VARCHAR(50)
	,@DIRECCION VARCHAR(100)

)
AS
BEGIN
    
    SET NOCOUNT ON
	
	UPDATE tiendas
	SET nombre_tienda = @NOMBRE_TIENDA
		,direccion = @DIRECCION
	WHERE id = @ID

END

GO
