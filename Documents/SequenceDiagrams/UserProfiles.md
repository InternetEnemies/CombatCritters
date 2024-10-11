```mermaid
sequenceDiagram
actor User
participant UI
participant API 
participant DB
User->>+UI: Enters details and hit login
UI->>+API: POST /users/auth/login
API->>+DB: get User from username
DB->>-API: User
API->>API: Verify Details
API->>-UI: 200 OK with User in body
UI->>-User: Logged in
```