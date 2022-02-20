SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_update_user]
(
	@ID INT
    ,@NAME VARCHAR(20)
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

)
AS
BEGIN
    
    SET NOCOUNT ON
	
	IF @PASSWORD_HASH IS NOT NULL AND @PASSWORD_SALT IS NOT NULL
		BEGIN

			UPDATE logings
			SET password_salt = @PASSWORD_SALT
				,password_hash = @PASSWORD_HASH
			FROM logings
				WHERE user_id = @ID

		END

	/*USER*/
	UPDATE users
	SET name = @NAME
		,surname = @SURNAME
		,dob = @DOB
		,id_number = @ID_NUMBER
	FROM users
		WHERE id = @ID
				
	/*ROL*/
	UPDATE user_rol
	SET role_id = @ROLE_ID
	FROM user_rol
		WHERE user_id = @ID
				
	/*LOGIN*/
	UPDATE logings
	SET user_name = @USERNAME
		,email = @EMAIL
	FROM logings
		WHERE user_id = @ID
				
	/*HORARIO - LUNES*/
	UPDATE user_schedule
	SET schedule_id = @Mon_SCHEDULE
	FROM user_schedule
		WHERE user_id = @ID
			AND weekday = 1
			
	/*HORARIO - MARTES*/
	UPDATE user_schedule
	SET schedule_id = @Tue_SCHEDULE
	FROM user_schedule
		WHERE user_id = @ID
			AND weekday = 2
			
	/*HORARIO - MIÉRCOLES*/
	UPDATE user_schedule
	SET schedule_id = @Wed_SCHEDULE
	FROM user_schedule
		WHERE user_id = @ID
			AND weekday = 1
				
	/*HORARIO - JUEVES*/	
	UPDATE user_schedule
	SET schedule_id = @Thu_SCHEDULE
	FROM user_schedule
		WHERE user_id = @ID
			AND weekday = 3
			
	/*HORARIO - VIERNES*/		
	UPDATE user_schedule
	SET schedule_id = @Fri_SCHEDULE
	FROM user_schedule
		WHERE user_id = @ID
			AND weekday = 4
							
	/*HORARIO - SÁBADO*/			
	UPDATE user_schedule
	SET schedule_id = @Sat_SCHEDULE
	FROM user_schedule
		WHERE user_id = @ID
			AND weekday = 5
							
	/*HORARIO - DOMINGO*/		
	UPDATE user_schedule
	SET schedule_id = @Sun_SCHEDULE
	FROM user_schedule
		WHERE user_id = @ID
			AND weekday = 6

/*

	/* USER NAME */
	IF ((SELECT name FROM users WHERE id = @ID) != @NAME)
		BEGIN
			UPDATE users
			SET name = @NAME
			FROM users
			WHERE ID = @ID
		END

	/* USER SURNAME */
	IF ((SELECT surname FROM users WHERE id = @ID) != @SURNAME)
		BEGIN
			UPDATE users
			SET name = @SURNAME
			FROM users
			WHERE ID = @ID
		END

	/* USER DOB */
	IF ((SELECT dob FROM users WHERE id = @ID) != @DOB)
		BEGIN
			UPDATE users
			SET name = @DOB
			FROM users
			WHERE ID = @ID
		END

	/* USER USERNAME */
	IF ((SELECT user_name FROM logings WHERE user_id = @ID) != @USERNAME)
		BEGIN
			UPDATE logings
			SET user_name = @USERNAME
			FROM logings
			WHERE user_id = @ID
		END

	/* USER ID NUMBER */
	IF ((SELECT id_number FROM users WHERE id = @ID) != @ID_NUMBER)
		BEGIN
			UPDATE users
			SET id_number = @ID_NUMBER
			FROM users
			WHERE ID = @ID
		END

	/* USER EMAIL */
	IF ((SELECT email FROM logings WHERE user_id = @ID) != @EMAIL)
		BEGIN
			UPDATE logings
			SET email = @EMAIL
			FROM logings
			WHERE user_id = @ID
		END

	/* USER ROLE ID */
	IF ((SELECT role_id FROM user_rol WHERE user_id = @ID) != @ROLE_ID)
		BEGIN
			UPDATE user_rol
			SET role_id = @ROLE_ID
			FROM user_rol
			WHERE user_id = @ID
		END

	/* USER MONDAY SCHEDULE */
	IF ((SELECT schedule_id FROM user_schedule WHERE user_id = @ID AND weekday = 0) != @Mon_SCHEDULE)
		BEGIN
			UPDATE user_schedule
			SET schedule_id = @Mon_SCHEDULE
			FROM user_schedule
			WHERE user_id = @ID
				AND weekday = 0
		END

	/* USER TUESDAY SCHEDULE */
	IF ((SELECT schedule_id FROM user_schedule WHERE user_id = @ID AND weekday = 1) != @Tue_SCHEDULE)
		BEGIN
			UPDATE user_schedule
			SET schedule_id = @Tue_SCHEDULE
			FROM user_schedule
			WHERE user_id = @ID
				AND weekday = 1
		END

	/* USER WEDNESDAY SCHEDULE */
	IF ((SELECT schedule_id FROM user_schedule WHERE user_id = @ID AND weekday = 2) != @Wed_SCHEDULE)
		BEGIN
			UPDATE user_schedule
			SET schedule_id = @Wed_SCHEDULE
			FROM user_schedule
			WHERE user_id = @ID
				AND weekday = 2
		END

	/* USER THURSDAY SCHEDULE */
	IF ((SELECT schedule_id FROM user_schedule WHERE user_id = @ID AND weekday = 3) != @Thu_SCHEDULE)
		BEGIN
			UPDATE user_schedule
			SET schedule_id = @Thu_SCHEDULE
			FROM user_schedule
			WHERE user_id = @ID
				AND weekday = 3
		END

	/* USER FRIDAY SCHEDULE */
	IF ((SELECT schedule_id FROM user_schedule WHERE user_id = @ID AND weekday = 4) != @Fri_SCHEDULE)
		BEGIN
			UPDATE user_schedule
			SET schedule_id = @Fri_SCHEDULE
			FROM user_schedule
			WHERE user_id = @ID
				AND weekday = 4
		END

	/* USER SATURDAY SCHEDULE */
	IF ((SELECT schedule_id FROM user_schedule WHERE user_id = @ID AND weekday = 5) != @Sat_SCHEDULE)
		BEGIN
			UPDATE user_schedule
			SET schedule_id = @Sat_SCHEDULE
			FROM user_schedule
			WHERE user_id = @ID
				AND weekday = 5
		END

	/* USER SUNDAY SCHEDULE */
	IF ((SELECT schedule_id FROM user_schedule WHERE user_id = @ID AND weekday = 6) != @Sun_SCHEDULE)
		BEGIN
			UPDATE user_schedule
			SET schedule_id = @Sun_SCHEDULE
			FROM user_schedule
			WHERE user_id = @ID
				AND weekday = 6
		END

	/* USER SURNAME */
	IF ((SELECT NAME FROM USERS WHERE ID = @ID) != @NAME)
		BEGIN
			UPDATE users
			SET name = @NAME
			FROM users
			WHERE ID = @ID
		END

	/* USER SURNAME */
	IF ((SELECT NAME FROM USERS WHERE ID = @ID) != @NAME)
		BEGIN
			UPDATE users
			SET name = @NAME
			FROM users
			WHERE ID = @ID
		END

	/* USER SURNAME */
	IF ((SELECT NAME FROM USERS WHERE ID = @ID) != @NAME)
		BEGIN
			UPDATE users
			SET name = @NAME
			FROM users
			WHERE ID = @ID
		END

	/* USER SURNAME */
	IF ((SELECT NAME FROM USERS WHERE ID = @ID) != @NAME)
		BEGIN
			UPDATE users
			SET name = @NAME
			FROM users
			WHERE ID = @ID
		END

	/* USER SURNAME */
	IF ((SELECT NAME FROM USERS WHERE ID = @ID) != @NAME)
		BEGIN
			UPDATE users
			SET name = @NAME
			FROM users
			WHERE ID = @ID
		END

	/* USER SURNAME */
	IF ((SELECT NAME FROM USERS WHERE ID = @ID) != @NAME)
		BEGIN
			UPDATE users
			SET name = @NAME
			FROM users
			WHERE ID = @ID
		END

	*/

END

GO
