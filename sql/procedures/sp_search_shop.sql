SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_search_shop]
(
	@ID INT
)
AS
BEGIN
    
    SET NOCOUNT ON

	SELECT *
	FROM tiendas
	WHERE id = @ID

END
GO
