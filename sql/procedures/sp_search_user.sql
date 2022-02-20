SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_search_user]
(
	@ID INT
)
AS
BEGIN
    
    SET NOCOUNT ON

	SELECT u.id,
		u.name,
		u.surname,
		u.dob,
		u.id_number,
		us.schedule_id,
		us.weekday,
		ur.role_id,
		l.email,
		l.user_name,
		ut.tienda_id
	FROM users u
		LEFT JOIN user_schedule us
			ON u.id = us.user_id
		LEFT JOIN user_rol ur
			ON u.id = ur.user_id
		LEFT JOIN logings l
			ON u.id = l.user_id
		LEFT JOIN user_tienda ut
			ON u.id = ut.user_id
	WHERE u.id = @ID

END
GO
