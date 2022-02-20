SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_update_item_type]
(
	@ID INT
	,@NAME VARCHAR(50)
)
AS
BEGIN
    
    SET NOCOUNT ON

	UPDATE tipo_prenda
	SET nombre = @NAME
	WHERE id = @ID

END
GO