# Notes

1. Authentication is implemented using HTTP Basic Auth with hardcoded demo credentials for simplicity. This approach is intentionally minimal and not intended for production use.


2. The requirements didn’t specify ordering semantics, so I chose `xs:all` for object-like groups to avoid imposing unnecessary constraints.


3. Update is implemented as a full replacement update (PUT-like). The request must contain the complete representation of the service. Partial updates are intentionally out of scope.


4. Only `ServiceId` is restricted to be non-empty in the XSD; other fields are validated for presence intentionally, since the specification defines no further value constraints.


5. While one may argue that the controller contains a bit of application-level logic, this choice is intentional for the given scope: extraction would be premature and add no real value yet at this stage.


6. `exceptionHandling` can be configured on the security filter to return custom SOAP bodies for 401 (Unauthorized) and 403 (Forbidden), but this is omitted for simplicity.
