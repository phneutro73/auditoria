SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_list_schedules]

AS
BEGIN
    
    SET NOCOUNT ON

	SELECT 
		id
		,schedule_name
		,check_in_time
		,check_out_time
	FROM schedules

END
GO
