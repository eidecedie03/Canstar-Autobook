using Canstar.Autobook.Data;
using Canstar.Autobook.Data.Entities;
using RestSharp;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace Canstar.AutoBook.Controllers
{
    public class UserController : ApiController
    {
        public bool Post(User user)
        {
            try
            {
                if (user.UserType == "Car Owner")
                {
                    Context context = new Context();
                    using (context)
                    {
                        context.Users.Add(user);
                        context.SaveChanges();
                    }

                    return true;
                }
                else
                {
                    var client = new RestClient("https://autobook-hm-2018.forge99.com");
                    var request = new RestRequest("people", Method.GET);
                    request.AddParameter("firstName_like", user.FirstName);
                    request.AddParameter("lastName_like", user.LastName);
                    request.AddParameter("email", user.EmailAddress);
                    var queryResult = client.Execute<List<Person>>(request).Data;

                    if (queryResult.Count > 0)
                    {
                        Context context = new Context();
                        using (context)
                        {
                                user.Avatar = queryResult[0].avatar.ToString();
                                user.score = queryResult[0].score.ToString();
                                user.Address = $"{queryResult[0].address.street} {queryResult[0].address.street_secondary}, {queryResult[0].address.city}";
                                user.GeoLocation = $"{queryResult[0].address.geo.latitude},{queryResult[0].address.geo.longitude}";
                                context.Users.Add(user);
                                context.SaveChanges();
                                return true;
                        }
                    }
                    else
                    {
                        return false;
                    }
                }
            }
            catch (Exception)
            {
                return false;
            }
        }

        public bool get(string email)
        {
            Context context = new Context();
            var userExistence = context.Users.Where(x => x.EmailAddress == email);
            if (userExistence != null)
            {
                return false;
            }
            else
            {
                return true;
            }
        }
    }
}
