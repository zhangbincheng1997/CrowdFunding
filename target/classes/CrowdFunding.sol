// https://solidity.readthedocs.io/en/develop/types.html#structs
pragma solidity ^0.5.0;

contract CrowdFunding {
    // Defines a new type with two fields.
    // Declaring a struct outside of a contract allows
    // it to be shared by multiple contracts.
    // Here, this is not really needed.
    struct Funder {
        address addr;
        uint amount;
    }

    // Structs can also be defined inside contracts, which makes them
    // visible only there and in derived contracts.
    struct Campaign {
        address payable beneficiary;
        uint fundingGoal;
        uint numFunders;
        uint amount;
        // mapping (uint => Funder) funders;
    }

    uint public numCampaigns;
    mapping (uint => Campaign) public campaigns;
    mapping (uint => mapping(uint => Funder)) public funders;

    function newCampaign(address payable beneficiary, uint goal) public returns (uint campaignID) {
        campaignID = numCampaigns++; // campaignID is return variable
        // Creates new struct in memory and copies it to storage.
        // We leave out the mapping type, because it is not valid in memory.
        // If structs are copied (even from storage to storage),
        // types that are not valid outside of storage (ex. mappings and array of mappings)
        // are always omitted, because they cannot be enumerated.
        campaigns[campaignID] = Campaign(beneficiary, goal, 0, 0);
    }

    function contribute(uint campaignID) public payable {
        Campaign storage c = campaigns[campaignID];
        // Creates a new temporary memory struct, initialised with the given values
        // and copies it over to storage.
        // Note that you can also use Funder(msg.sender, msg.value) to initialise.
        // c.funders[c.numFunders++] = Funder({addr: msg.sender, amount: msg.value});
        funders[campaignID][c.numFunders++] = Funder({addr: msg.sender, amount: msg.value});
        c.amount += msg.value;
    }

    function checkGoalReached(uint campaignID) public returns (bool reached) {
        Campaign storage c = campaigns[campaignID];
        if (c.amount < c.fundingGoal)
            return false;
        uint amount = c.amount;
        c.amount = 0;
        c.beneficiary.transfer(amount);
        return true;
    }
}