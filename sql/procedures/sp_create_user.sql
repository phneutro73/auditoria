SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_create_user]
(
    @NAME VARCHAR(20)
	,@SURNAME VARCHAR(40)
	,@DOB DATE
	,@USERNAME VARCHAR(40)
	,@ID_NUMBER VARCHAR(20)
	,@EMAIL VARCHAR(255)
	,@PASSWORD_SALT VARCHAR(MAX)
	,@PASSWORD_HASH VARCHAR(MAX)
	,@Mon_SCHEDULE INT
	,@Tue_SCHEDULE INT
	,@Wed_SCHEDULE INT
	,@Thu_SCHEDULE INT
	,@Fri_SCHEDULE INT
	,@Sat_SCHEDULE INT
	,@Sun_SCHEDULE INT
	,@ROLE_ID INT
	,@SHOP_ID INT
)
AS
BEGIN
    
    SET NOCOUNT ON

	INSERT INTO users
	VALUES
		(@NAME
		,@SURNAME
		,@DOB
		,@ID_NUMBER)

	DECLARE @user_id INT = SCOPE_IDENTITY()

	INSERT INTO logings
	VALUES
		(@USERNAME
		,@EMAIL
		,@PASSWORD_SALT
		,@PASSWORD_HASH
		,@user_id
		)

	INSERT INTO user_schedule
	VALUES
	(@user_id,@Mon_SCHEDULE,0)
	,(@user_id,@Tue_SCHEDULE,1)
	,(@user_id,@Wed_SCHEDULE,2)
	,(@user_id,@Thu_SCHEDULE,3)
	,(@user_id,@Fri_SCHEDULE,4)
	,(@user_id,@Sat_SCHEDULE,5)
	,(@user_id,@Sun_SCHEDULE,6)

	INSERT INTO user_rol
	VALUES (@user_id, @ROLE_ID)

	INSERT INTO user_tienda
	VALUES (@user_id, @SHOP_ID)

END
GO
