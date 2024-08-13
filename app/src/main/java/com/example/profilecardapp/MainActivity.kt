package com.example.profilecardapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import com.example.profilecardapp.ui.theme.LightGreen
import com.example.profilecardapp.ui.theme.LightRed
import com.example.profilecardapp.ui.theme.ProfileCardAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProfileCardAppTheme {
                ProfileApplication(profileList = profileList, modifier = Modifier)
            }
        }
    }
}

@Composable
fun ProfileApplication(profileList: List<Profile>, modifier: Modifier = Modifier){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "profile_list") {
        composable(route = "profile_list"){
            ProfileListScreen(profileList = profileList, modifier = modifier, navController = navController)
        }
        composable(route = "profile_details/{user_id}",
            arguments = listOf(navArgument("user_id") {type = NavType.IntType})){ backStackEntry ->
            val selectedProfile = profileList.first { profile -> profile.id == backStackEntry.arguments?.getInt("user_id") }
            ProfileDetailScreen(profile = selectedProfile, modifier = modifier, navController = navController)
        }
    }
}


@Composable
fun ProfileListScreen(profileList: List<Profile>, modifier: Modifier = Modifier, navController: NavController?){
    Scaffold(
        topBar = { AppBar(title = "Profile List", icon = Icons.Filled.Home, onIconClick = {}, modifier = modifier)},
        modifier = Modifier.fillMaxSize()) { innerPadding ->
        ProfileList(
            profileList =  profileList,
            modifier = Modifier.padding(innerPadding),
            navController = navController
        )
    }
}

@Composable
fun ProfileDetailScreen(profile: Profile, modifier: Modifier = Modifier, navController: NavController?){
    Scaffold(topBar = { AppBar(title = "Profile Details",
        icon = Icons.Filled.ArrowBack){
        navController?.popBackStack()
    }},
        modifier = modifier.fillMaxSize(),)
    { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .padding(top = 30.dp)
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProfilePicture(profilePictureUrl = profile.profilePictureUrl, profileStatus = profile.status, imageSize = 250.dp, modifier = modifier)
            ProfileContent(profileName = profile.name, profileStatus = profile.status, horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier.padding(top = 20.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(title: String, icon: ImageVector, modifier: Modifier = Modifier, onIconClick: () -> Unit) {
    TopAppBar(
        title = {
            Row {
                Icon(imageVector = icon,
                    contentDescription = "", modifier = modifier
                        .padding(end = 10.dp)
                        .clickable { onIconClick.invoke() })
                Text(text = title)
            }
        },
        colors = TopAppBarColors(
            containerColor = LightGreen,
            actionIconContentColor = Color.Black,
            titleContentColor = Color.White,
            navigationIconContentColor = Color.Black,
            scrolledContainerColor = Color.LightGray
        ),
        windowInsets = WindowInsets(left = 10.dp, top = 20.dp)
    )
}

@Composable
fun ProfileList(profileList: List<Profile>, modifier: Modifier = Modifier, navController: NavController?){
    Surface(modifier = modifier) {

        LazyColumn {
            items(profileList){
                profile: Profile ->  ProfileCard(
                profile = profile,
                 ){ navController?.navigate(route = "profile_details/${profile.id}") }
            }
        }
//        Column {
//            for(profile in profileList){
//                ProfileCard(profile = profile, modifier)
//            }
//        }
    }
}

@Composable
fun ProfileCard(profile: Profile, modifier: Modifier = Modifier, onClick: () -> Unit){
    Card (
        shape = CutCornerShape(topEnd = 15.dp),
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable { onClick.invoke() },
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        colors =
        CardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer,
            contentColor = MaterialTheme.colorScheme.onSurface,
            disabledContentColor = MaterialTheme.colorScheme.tertiaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.tertiaryContainer)) {
        Row (modifier = Modifier
            .padding(all = 8.dp), verticalAlignment = Alignment.CenterVertically){
            ProfilePicture(profilePictureUrl = profile.profilePictureUrl, profileStatus = profile.status, imageSize = 75.dp)
            Box(modifier = Modifier.width(18.dp))
            ProfileContent(profileName = profile.name, profileStatus = profile.status, modifier = Modifier, horizontalAlignment = Alignment.Start)
        }
    }
}

@Composable
fun ProfilePicture(profilePictureUrl: String, profileStatus: Boolean, imageSize: Dp, modifier: Modifier = Modifier){
    Card(modifier = modifier.height(imageSize), shape = CircleShape, border = BorderStroke(width = 4.dp, color = if(profileStatus) LightGreen else LightRed)){
        AsyncImage(model = profilePictureUrl,
            contentDescription = "Profile Picture",
            modifier = modifier
                .height(imageSize)
                .width(imageSize)
                .clip(CircleShape),

            )
    }
}

@Composable
fun ProfileContent(
    profileName: String,
    profileStatus: Boolean,
    modifier: Modifier = Modifier,
    horizontalAlignment: Alignment.Horizontal
    ){
    Column(modifier = modifier.fillMaxWidth(),
            horizontalAlignment = horizontalAlignment
        ) {
        Text(text = profileName, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Text(text = if(profileStatus) "Active Now" else "Offline", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.alpha(
            0.7F
        ))
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileListPreview(){
    ProfileCardAppTheme{
        ProfileListScreen(profileList = profileList, Modifier, null)
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileListDetail(){
    ProfileCardAppTheme{
        ProfileDetailScreen(profile = profileList[0], modifier = Modifier, navController = null)
    }
}
