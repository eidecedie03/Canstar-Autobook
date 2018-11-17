using Canstar.Autobook.Data;
using Canstar.Autobook.Data.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace Canstar.AutoBook.Controllers
{
    public class LoginController : ApiController
    {
        public HttpResponseMessage Post(User userx)
        {
            try
            {
                var user = new User();

                Context context = new Context();
                using (context)
                {
                    user = context.Users.Where(x => x.EmailAddress == userx.EmailAddress && x.Password == userx.Password).ToList().Last();
                }
                user.Error = "false";
                return this.Request.CreateResponse(HttpStatusCode.OK, user);
            }
            catch (Exception)
            {
                return this.Request.CreateResponse(HttpStatusCode.OK, new { error = "True", message = "Invalid username or password" });
            }
        }
    }
}
