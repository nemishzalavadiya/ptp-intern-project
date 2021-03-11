import React,{useState,useEffect} from "react";
import {addKYCDetails,isKycVerified} from "src/services/KYCService";
import {
  Grid,
  Icon,
  Divider,
  Image,
  Loader
} from "semantic-ui-react";
import PanDetail from "src/components/KYC/PanDetail";
import PersonalDetail from "src/components/KYC/PersonalDetail";
import Signature from 'src/components/KYC/Signature';
import SuccessPage from 'src/components/KYC/SuccessPage';

import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { useRouter } from 'next/router'


export default function KYC(props) {
const router = useRouter();
const [isContentFetchingCompleted,changeFetchStatus]=useState(false);
const [page,setPage]=useState(0);
const [isValid,setValid]=useState(false);

useEffect(() => {
  changeFetchStatus(true);
  const data=isKycVerified().then(res=>{
    if(res)
    {
      router.push("/profile");
    }
    else
    {
      changeFetchStatus(true);
    }
    });
  
}, [])

const [panDetails,setPanDetails]=useState({
  panNumber:'',
  panFile:null,
});

const [personalDetails,setPersonalDetails]=useState({
  occupation:'',
  annualIncome:'',
  motherName:'',
  fatherName:''
});

const [leagleInfo,setLeagleInfo]=useState({
  sign:null,
  profile:null
});

const headers=[
  {
    header:'Enter Your PAN Details',
    subHeader:''
  },
  {
    header:'Enter Your Personal Details',
    subHeader:''
  },
  {
    header:'Upload Your Signature and Profile',
    subHeader:'Your signature is required to open stock demat account'
  }
]


const nextClick=()=>{
    setPage(page+1);
}

const prevClick=()=>{
    setPage(page-1);
}
function createFormData()
{
  const formData = new FormData();
  formData.append("pan",panDetails.panFile);
  formData.append("signature",leagleInfo.sign);
  formData.append("profile  ",leagleInfo.profile);
  formData.append("panNumber",panDetails.panNumber);
  formData.append("occupation",personalDetails.occupation);
  formData.append("annualIncome",personalDetails.annualIncome);
  formData.append("motherName",personalDetails.motherName);
  formData.append("fatherName",personalDetails.fatherName);
  formData.append("pab",panDetails.panFile);
  return formData;
}


const validatePan=()=>{
  var patt = new RegExp("[A-Z]{5}[0-9]{4}[A-Z]{1}");
  if(!patt.test(panDetails.panNumber))
  {
    setValid(false);
    toast.error("Enter Valid PAN Number", {
      position: "bottom-right",
      autoClose: 2000,
      hideProgressBar: false,
    });
 }
 if(panDetails.panFile===null)
 {
  toast.error("PAN Card is Required", {
    position: "bottom-right",
    autoClose: 2000,
    hideProgressBar: false,
  });
  setValid(false);
 }
}

const validatePersonalDetails=()=>{
  if(personalDetails.occupation===null||personalDetails.annualIncome===''||personalDetails.motherName===''||personalDetails.fatherName==='')
  {
    toast.error("Enter Valid Personal Details", {
      position: "bottom-right",
      autoClose: 2000,
      hideProgressBar: false,
    });
    setValid(false);
  }
}

const validateLeagleInfo=()=>{
  if(leagleInfo.profile===null||leagleInfo.sign===null)
  {
    toast.error("Signature and Profile are required", {
      position: "bottom-right",
      autoClose: 2000,
      hideProgressBar: false,
    });
    setValid(false);
  }
}


const upload=async ()=> {
  setValid(true);
  validatePan();
  validatePersonalDetails();
  validateLeagleInfo();
  if(isValid){
    await addKYCDetails(createFormData()).then(()=>{
      toast.success("Verification Done", {
        position: "bottom-right",
        autoClose: 2000,
        hideProgressBar: false,
      });
      nextClick();
    
  }).catch((err)=>{
    toast.error(err.message, {
      position: "bottom-right",
      autoClose: 2000,
      hideProgressBar: false,
    });
  });

  }
}


function renderSwitch() {
    switch(page) {
        case 0:
            return <PanDetail panDetails={panDetails} setPanDetails={setPanDetails}/>;
        case 1:
            return <PersonalDetail personalDetails={personalDetails} setPersonalDetails={setPersonalDetails}/>;
        case 2:
            return <Signature leagleInfo={leagleInfo} setLeagleInfo={setLeagleInfo} isValid={isValid} upload={upload} nextClick={nextClick}/>;
        default:
            return 'foo';
    }
  }

  return (
    isContentFetchingCompleted?(page!==3?(<div className="kycdiv">
      <ToastContainer/>
      <Grid textAlign="center">
        <Grid.Row>
          <Grid.Column width={7} className="leftSide">
            <Image src="/kyc.png"></Image>
          </Grid.Column>
          <Grid.Column width={7} verticalAlign="top" className="rightSide">
          <div className="kycTicket">
          <div className="header">
          <div><Icon name='angle double left' disabled={page==0} onClick={prevClick}/>Back</div>
          <div>Next<Icon name='angle double right' disabled={page==2} onClick={nextClick}/></div>
          </div>
          <h3>{headers[page].header}</h3>
          <h5>{headers[page].subHeader}</h5>
          <Divider></Divider>
          <div>
          
           {
               renderSwitch()
           }
           </div>
           </div>
          </Grid.Column>
        </Grid.Row>
      </Grid>
    </div>):(<SuccessPage panDetails={panDetails} personalDetails={personalDetails} leagleInfo={leagleInfo}></SuccessPage>)):<Loader active inverted>Loading...</Loader>
  );
}
