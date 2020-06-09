// https://solidity.readthedocs.io/en/develop/types.html#structs
pragma solidity ^0.5.0;

contract CrowdFunding {

    struct Funder {
        address addr;
        uint amount;

        uint time;
    }

    struct Campaign {
        address payable beneficiary;
        string content;
        uint goal;
        uint amount;

        uint numFunders;
        mapping(uint => Funder) funders;

        uint time;
    }

    uint public numCampaigns;
    mapping(uint => Campaign) public campaigns;

    function numFunders(uint campaignID) public view returns(uint) {
        Campaign storage c = campaigns[campaignID];
        return c.numFunders;
    }

    function funders(uint campaignID, uint funderID) public view returns(address, uint, uint) {
        Funder storage f = campaigns[campaignID].funders[funderID];
        return (f.addr, f.amount, f.time);
    }

    function create(string memory content, uint goal) public {
        campaigns[numCampaigns++] = Campaign(msg.sender, content, goal, 0, 0, now);
    }

    function contribute(uint campaignID) public payable {
        Campaign storage c = campaigns[campaignID];
        c.beneficiary.transfer(msg.value);
        c.amount += msg.value;
        c.funders[c.numFunders++] = Funder(msg.sender, msg.value, now);
    }
}