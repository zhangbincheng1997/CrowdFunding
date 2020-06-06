// https://solidity.readthedocs.io/en/develop/types.html#structs
pragma solidity ^0.5.0;

contract CrowdFunding {

    struct Funder {
        address addr;
        uint amount;
    }

    struct Campaign {
        address payable beneficiary;
        string content;
        uint goal;
        uint amount;
        uint numFunders;
    }

    uint public numCampaigns;
    mapping(uint => Campaign) public campaigns;
    mapping(uint => mapping(uint => Funder)) public funders;

    function sponsor(string memory content, uint goal) public {
        campaigns[numCampaigns++] = Campaign(msg.sender, content, goal, 0, 0);
    }

    function contribute(uint campaignID) public payable {
        Campaign storage c = campaigns[campaignID];
        c.beneficiary.transfer(msg.value);
        c.amount += msg.value;
        funders[numCampaigns][c.numFunders++] = Funder(msg.sender, msg.value);
    }
}