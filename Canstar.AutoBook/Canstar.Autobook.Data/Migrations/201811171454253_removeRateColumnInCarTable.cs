namespace Canstar.Autobook.Data.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class removeRateColumnInCarTable : DbMigration
    {
        public override void Up()
        {
            DropColumn("dbo.Cars", "Rate");
        }
        
        public override void Down()
        {
            AddColumn("dbo.Cars", "Rate", c => c.String());
        }
    }
}
