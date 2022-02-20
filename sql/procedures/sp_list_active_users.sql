SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_list_active_users]

AS
BEGIN
    
    SET NOCOUNT ON

	SELECT
		u.id
		,u.name 
		,u.surname
		,u.id_number
		,u.dob
		,ur.role_id
		,r.role_name
		,t.id
		,t.nombre_tienda
	FROM users u
		LEFT JOIN user_rol ur
			ON u.id = ur.user_id
		LEFT JOIN roles r
			ON r.id = ur.role_id
		LEFT JOIN user_tienda ut
			ON u.id = ut.user_id
		LEFT JOIN tiendas t
			ON ut.tienda_id = t.id

END
GO
