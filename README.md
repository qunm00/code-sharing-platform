# code-sharing-platform

### Project Description:
[Hyperskill - Code Sharing Platform](https://hyperskill.org/projects/130?track=12)

### Cloud Deployment:
http://code-sharing.azurewebsites.net/

### Code snippet:
<pre>
{
    "code": "class Code { ...",
    "time": 0,
    "views": 0
}

</pre>

### Routes:
<pre>
/code/new  
/code/{id}  
/code/latest  

POST /api/code/new  
GET /api/code/{id}  
GET /api/code/latest  
</pre>

### Need improvement:
 - Expired codes aren't deleted from database.
 - There is no notification if invalid from is submitted
