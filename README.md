cd .\src\main\resources\static\certs\
openssl genrsa -out keypair.pem 2048
openssl rsa -in keypair.pem -pubout -out public.pem
openssl pkcs8 -topk8 -inform PEM -nocrypt -in keypair.pem -out private.pem

delete keypair.pem 

user: user@user.com
pass: user

link 
- /api/auth/login
- /api/recruitment/positions
- /api/recruitment/positions/{id}
