SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_list_item_types]

AS
BEGIN
    
    SET NOCOUNT ON

	SELECT id
			,nombre
	FROM tipo_prenda

END
GO
