SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_list_roles]

AS
BEGIN
    
    SET NOCOUNT ON

	SELECT r.id,
		r.role_name,
		COUNT(ur.user_id) 'count_users_role'
	FROM roles r
		LEFT JOIN user_rol ur
			ON r.id = ur.role_id
	GROUP BY r.id, r.role_name

END
GO
