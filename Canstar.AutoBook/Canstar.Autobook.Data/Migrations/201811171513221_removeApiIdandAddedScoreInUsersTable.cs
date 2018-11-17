namespace Canstar.Autobook.Data.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class removeApiIdandAddedScoreInUsersTable : DbMigration
    {
        public override void Up()
        {
            AddColumn("dbo.Users", "score", c => c.String());
            DropColumn("dbo.Users", "ApiId");
        }
        
        public override void Down()
        {
            AddColumn("dbo.Users", "ApiId", c => c.String());
            DropColumn("dbo.Users", "score");
        }
    }
}
