SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_delete_schedule]
(
	@ID INT
)
AS
BEGIN
    
    SET NOCOUNT ON

	DELETE
	FROM schedules
	WHERE id = @ID

END
GO