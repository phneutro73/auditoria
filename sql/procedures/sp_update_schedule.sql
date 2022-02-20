SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_update_schedule]
(
	@ID INT
    ,@NAME VARCHAR(20)
	,@CHECK_IN_TIME VARCHAR(8)
	,@CHECK_OUT_TIME VARCHAR(8)

)
AS
BEGIN
    
    SET NOCOUNT ON
	
	UPDATE schedules
	SET schedule_name = @NAME
		,check_in_time = @CHECK_IN_TIME
		,check_out_time = @CHECK_OUT_TIME
	WHERE id = @ID

END

GO
