namespace Canstar.Autobook.Data.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class ChangedCarTypeToCarBrandInCarTable : DbMigration
    {
        public override void Up()
        {
            AddColumn("dbo.Cars", "CarBrand", c => c.String());
            DropColumn("dbo.Cars", "CarType");
        }
        
        public override void Down()
        {
            AddColumn("dbo.Cars", "CarType", c => c.String());
            DropColumn("dbo.Cars", "CarBrand");
        }
    }
}
