# Generate key 
cd .\src\main\resources\static\certs\
1. openssl genrsa -out keypair.pem 2048
2. openssl rsa -in keypair.pem -pubout -out public.pem
3. openssl pkcs8 -topk8 -inform PEM -nocrypt -in keypair.pem -out private.pem

delete keypair.pem 

# Info DB
using db mysql
create database test_dansmultipro

# Info user
user: user@user.com
pass: user

# link api
link 
- /api/auth/login
- /api/recruitment/positions
- /api/recruitment/positions/{id}
- /api/recruitment/positions/filter-by-id
- /api/recruitment/positions/group-location
