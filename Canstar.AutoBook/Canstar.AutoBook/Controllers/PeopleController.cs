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
    public class PeopleController : ApiController
    {
        public IEnumerable<Person> Get()
        {
            var client = new RestClient("https://autobook-hm-2018.forge99.com");
            var request = new RestRequest("people?_limit=8000&_page=2", Method.GET);
            //request.AddParameter("_limit", "100", ParameterType.UrlSegment); &_page=2

            var people = client.Execute<List<Person>>(request).Data;


            Context context = new Context();
            using (context)
            {
                foreach (var person in people)
                {
                    var user = new User();

                    user.LastName = person.lastName;
                    user.FirstName = person.firstName;
                    user.EmailAddress = person.email;

                    string[] randomNumbers = { "09123456789", "091122334455", "093322114466", "0912352123", "0912321123", "091235347823", "091231257832", "09123861232" }; user.Number = randomNumbers[new Random().Next(0, randomNumbers.Length)];

                    string[] randomBirthday = { "12/16/1992", "11/12/1982", "12/17/1964", "01/12/1902", "04/16/1923", "05/18/1907", "07/10/1927", "09/11/1998" };
                    user.Birthday = randomBirthday[new Random().Next(0, randomBirthday.Length)];

                    string[] randomUserType = { "User" };
                    user.UserType = randomUserType[new Random().Next(0, randomUserType.Length)]; ;

                    user.Password = person.firstName.Substring(0, 1) + person.lastName;
                    user.Avatar = person.avatar.ToString();
                    user.score = person.score.ToString();
                    user.Address = $"{person.address.street} {person.address.street_secondary}, {person.address.city}";
                    user.GeoLocation = $"{person.address.geo.latitude},{person.address.geo.longitude}";

                    context.Users.Add(user);
                }
                context.SaveChanges();
            }

            return null;
        }
    }
}