namespace Canstar.Autobook.Data.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class AddedBelowAverageInCarsTable : DbMigration
    {
        public override void Up()
        {
            AddColumn("dbo.Cars", "AllowBelowAveScore", c => c.Boolean(nullable: false));
            
        }
        
        public override void Down()
        {
            DropColumn("dbo.Cars", "AllowBelowAveScore");
        }
    }
}
