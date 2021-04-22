# Student-Request-Management-System-Backend

✔✔✔✔
create  develop branch and add other branch 
❌❌❌❌❌ Don't merge it to main directly


USER ADMINISTRATION API'S
Following are the implemented API for user administration Method API Action Method API Action Registration (requires header Authorization) POST /admin/register/student/single Registration of single Student

POST /admin/register/student/batch/upload Registration of multiple students using a excel file upload/admin/register/student/batch/saveAll 

POST /admin/register/fac Register a FAC member

POST /admin/register/ar Register assistant Registrar

POST /admin/register/ugs Register UGS staff member/adminLogin and Forgot Password (permitted for all without header)

POST /all/login Login with username and password credentials: Login Request DTO 

POST /all/changePassword Search dB with username if found username sends an email with new password or else exception is returnedUser Information Change 

POST /admin/updatePassword update password by user when he is logged in to the systemGet Information about users

GET /admin/academicAdvisors shows all fac members who are academic advisors as output.ResponseEntity: AcademicAdvisorListResponse

GET /admin/student/getUserInfo/{id} can retrieve information about the specific student: ResponseEntity: StudentInformationResponse GET /admin/fac/getUserInfo/{id} can retrieve information about the specific fac member: ResponseEntity: FacInformationResponse GET /admin/ar /getUserInfo/{id} can retrieve information about the specific Assistant Registrar: ResponseEntity: FacInformationResponse GET /admin/ugs /getUserInfo/{id} can retrieve information about the specific ugs: ResponseEntity: FacInformationResponse
