pragma solidity >=0.5.0;
pragma experimental ABIEncoderV2;

contract Transfer{

    struct Passbook{
        uint256 count;
        address ngo;
    }
    mapping (address => Passbook[]) Passbooks ;
    address[] senderaccounts;
    uint256[] sum;
    function setTransaction(address payable _ngo) public payable{
        uint256 c = 0;
        for(uint256 i = 0;i<Passbooks[msg.sender].length;i++){
            if(Passbooks[msg.sender][i].ngo == _ngo){
                c = 1;
                Passbooks[msg.sender][i].count = Passbooks[msg.sender][i].count + msg.value;
            }}
            if(c!=1){
                 Passbook memory newEntry = Passbook(msg.value,_ngo);
                Passbooks[msg.sender].push(newEntry);
                senderaccounts.push(msg.sender);
            }
        _ngo.transfer(msg.value);
    }

    function getNGODonaterCount(address _ngo) public returns(uint[] memory){
           delete sum;
           for(uint i = 0;i < senderaccounts.length;i++){
            for(uint j = 0;j < Passbooks[senderaccounts[i]].length;j++){
                if(Passbooks[senderaccounts[i]][j].ngo==_ngo){
                    sum.push(Passbooks[senderaccounts[i]][j].count);
                }
            }
        }
        return sum;
    }
   function getAllSenderAccount() public view returns(address[] memory){
        return (senderaccounts);
    }
    function getAllInfoByAccount(address _sender) public view returns(Passbook[] memory){
        return Passbooks[_sender];
    }

}