namespace Canstar.Autobook.Data.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class addedSomeFields : DbMigration
    {
        public override void Up()
        {
            AddColumn("dbo.Users", "GeoLocation", c => c.String());
            AddColumn("dbo.Users", "Avatar", c => c.String());
            AddColumn("dbo.Users", "UserType", c => c.String());
            AddColumn("dbo.Users", "ApiId", c => c.String());
            AddColumn("dbo.Users", "Password", c => c.String());
        }
        
        public override void Down()
        {
            DropColumn("dbo.Users", "Password");
            DropColumn("dbo.Users", "ApiId");
            DropColumn("dbo.Users", "UserType");
            DropColumn("dbo.Users", "Avatar");
            DropColumn("dbo.Users", "GeoLocation");
        }
    }
}
