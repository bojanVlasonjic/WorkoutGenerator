INSERT INTO authority(id, role) VALUES (-1, "ROLE_ADMIN")
INSERT INTO authority(id, role) VALUES (-2, "ROLE_USER")

------ USERS ------
insert into app_user(id, email, first_name, last_name, password, weight, user_level, repetition_factor, work_load_factor, work_interval_factor, upper_body_worked) values (-1, "user@email.com", "dummy", "dummy", "password", 70, 1, 1, 1, 0, false)
insert into app_user_authorities(app_user_id, authorities_id) values (-1, -2)

insert into admin(id, email, first_name, last_name, password) values (-1, "admin@email.com", "admin", "admin", "admin")
insert into admin_authorities(admin_id, authorities_id) values (-1, -1)

----- BARBELL EXERCISES -----
insert into exercise(id, name, equipment, exercise_type, description) values (-1, "Front squat", 1, 0,"Begin with the barbell across the front side of your shoulders. Place your fingertips under the barbell just outside of your shoulders and drive your elbows up. Keeping your chest up and core tight, bend at your hips and knees to lower into a squat until your thighs are parallel to the ground. Straighten your hips and knees to drive up to the starting position.")
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-1, 6)
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-1, 8)

insert into exercise(id, name, equipment, exercise_type, description) values (-6, "Back squat", 1, 0,"Place barbell behind neck. The bar is somewhat lower than your shoulders. The feet are quite apart and point out. The chest is out. Go now slowly down, till your thighs are parallel with the floor, not lower. The knees point outwards, your butt, out. Make a small pause of 1 second and with as much energy as you can, push the weight up. Make a pause of 2 seconds and repeat.")
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-6, 6)
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-6, 8)

insert into exercise(id, name, equipment, exercise_type, description) values (-7, "Lunges", 1, 0,"Put barbell on the back of your shoulders. Stand upright, then take the first step forward. Step should bring you forward so that your supporting legs knee can touch the floor. Then stand back up and repeat with the other leg.")
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-7, 6)
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-7, 8)

insert into exercise(id, name, equipment, exercise_type, description) values (-2, "Bent over row", 1, 0, "Grab the barbell with a wide grip (slightly more than shoulder wide) and lean forward. Your upper body is not quite parallel to the floor, but forms a slight angle. The chest's out during the whole exercise. Pull now the barbell with a fast movement towards your belly button, not further up. Go slowly down to the initial position. Don't swing with your body and keep your arms next to your body.")
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-2, 4)

insert into exercise(id, name, equipment, exercise_type, description) values (-3, "Bench press", 1, 0, "Lay down on a bench, the bar should be directly above your eyes, the knees are somewhat angled and the feet are firmly on the floor. Concentrate, breath deeply and grab the bar more than shoulder wide. Bring it slowly down till it briefly touches your chest at the height of your nipples. Push the bar up.")
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-3, 3)

insert into exercise(id, name, equipment, exercise_type, description) values (-4, "Seated shoulder press", 1, 0, "Sit on a bench, the back rest should be almost vertical. Take a barbell with a shoulder wide grip and bring it up to chest height. Press the weight up, but don't stretch the arms completely. Go slowly down and repeat.")
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-4, 0)

insert into exercise(id, name, equipment, exercise_type, description) values (-5, "Shrugs", 1, 0, "Take a barbell and stand with a straight body, the arms are hanging freely in front of you. Lift from this position the shoulders as high as you can, but don't bend the arms during the movement. On the highest point, make a short pause of 1 or 2 seconds before returning slowly to the initial position.")
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-5, 0)

insert into exercise(id, name, equipment, exercise_type, description) values (-8, "Ab rollout", 1, 0, "Place a barbell on the floor at your feet. Bending at the waist, grip the barbell with a shoulder with overhand grip. With a slow controlled motion, roll the bar out so that your back is straight. Roll back up raising your hips and butt as you return to the starting position.")
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-8, 5)
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-8, 0)

insert into exercise(id, name, equipment, exercise_type, description) values (-17, "Stiff legged deadlift", 1, 0, "Hold the bar at hip level. Keeping your back straight, core engaged, shoulder blades pulled back and legs locked out slowly descend with the bar until your torso is parallel with the floor. Hold for a second and go back. Keep back straight.")
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-17, 4)
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-17, 7)
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-17, 8)

insert into exercise(id, name, equipment, exercise_type, description) values (-18, "Deadlift", 1, 0, "Stand firmly, with your feet slightly more than shoulder wide apart. Stand directly behind the bar where it should barely touch your shin, your feet pointing a bit out. Bend down with a straight back, the knees also pointing somewhat out. Grab the bar with a shoulder wide grip, one underhand, one reverse grip. Pull the weight up. At the highest point make a slight hollow back and pull the bar back. Hold 1 or 2 seconds that position. Go down, making sure the back is not bent. Once down you can either go back again as soon as the weights touch the floor, or make a pause, depending on the weight.")
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-18, 4)
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-18, 6)
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-18, 8)


insert into exercise(id, name, equipment, exercise_type, description) values (-37, "Seated calf raises", 1, 0, "Sit on a bench. Place a barbell above your knees. Put a towel or pillow beneath the bar if you feel uncomfortable. Keep your legs bent at a 90 degree angle, and place your toes on an elevated object (a brick of example). Raise your heels so that they are above your toes. Hold for 2 seconds, then slowly descend until your heels reach the floor. Repeat.")
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-37, 9)

insert into exercise(id, name, equipment, exercise_type, description) values (-38, "Standing calf raises", 1, 0, "Stand on the edge of an elevated surface (small step, brick, plates) with a barbell placed behind your neck. Stand tall with your abdominals pulled in, the balls of your feet firmly planted on the step, and your heels hanging over the edge. Raise your heels a few inches above the edge of the step so that you’re on your tiptoes. Hold the position for a moment, and then lower your heels below the platform, feeling a stretch in your calf muscles.")
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-38, 9)

----- PULL UP BAR EXERCISES -----
insert into exercise(id, name, equipment, exercise_type, description) values (-9, "Chin ups", 2, 0, "Grab bar with palm and fingers facing towards yourself. Hang from the bar, pull yourself up to the top so that your chin is above the bar. Slowly descend to lock out. Stay in hang position for a second. Repeat.")
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-9, 4)
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-9, 2)

insert into exercise(id, name, equipment, exercise_type, description) values (-10, "Pull ups", 2, 0, "Grab the pull up bar with a wide grip, the body is hanging freely. Keep your chest out and pull yourself up till your chin reaches the bar or it touches your neck, if you want to pull behind you. Go with a slow and controlled movement down, always keeping the chest out.")
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-10, 4)
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-10, 2)

insert into exercise(id, name, equipment, exercise_type, description) values (-11, "Toes to bar", 2, 0, "Grab the pull up bar and hang freely from it. Keeping your head up, back and core engaged, and legs straight try to reach the top of the bar with your toes.")
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-11, 4)
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-11, 5)

insert into exercise(id, name, equipment, exercise_type, description) values (-12, "Knees to chest", 2, 2, "Grab the pull up bar and hang freely from it. Keeping your head up, back and core engaged, pull your knees towards your chest. Hold them there for a second and slowly descend them. Repeat.")
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-12, 4)
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-12, 5)

----- KETTLEBELL EXERCISES -----
insert into exercise(id, name, equipment, exercise_type, description) values (-13, "Clean and press", 5, 2, "Keeping your back straight and core engaged reach the kettlebell from floor. Using the momentum from your legs and hips pull the kettlebell and place it into front rack position.  Widen stance slightly, drop a couple inches like you're beginning a squat, and then fire glutes and quads in conjunction with shoulders to put kettlebell over your head. Put the kettlebell down to the ground and switch hands.")
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-13, 0)
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-13, 6)

insert into exercise(id, name, equipment, exercise_type, description) values (-14, "Kettlebell swing", 5, 1, "Grab the kettlebell from floor and hold it with extended arms in front of you. Using the momentum from your legs and hips and keeping arms straight swing the kettlebell to face level, swing it back down and repeat.")
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-14, 4)
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-14, 8)
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-14, 7)

insert into exercise(id, name, equipment, exercise_type, description) values (-15, "Single leg deadlift", 5, 2, "Hold a kettlebell in one hand, hanging to the side. Stand on one leg, on the same side that you hold the kettlebell. Keeping that knee slightly bent, perform a stiff-legged deadlift by bending at the hip, extending your free leg behind you for balance. Continue lowering the kettlebell until you are parallel to the ground, and then return to the upright position. Repeat for the desired number of repetitions.")
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-15, 4)
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-15, 6)

insert into exercise(id, name, equipment, exercise_type, description) values (-16, "Goblet squat", 5, 2, "Hold a kettlebell with both hands at your chest. Keeping your back straight, core engaged slowly descend into squat position by pulling your hips backwards. Reach as low as you can and descend back up.")
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-16, 6)
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-16, 8)

----- DUMBBELL EXERCISES -----
insert into exercise(id, name, equipment, exercise_type, description) values (-19, "Walking lunges ", 4, 2, "Take two dumbbells in your hands, stand straight, feet about shoulder wide. Take one long step so that the front knee is approximately forming a right angle. The back leg is streched, the knee is low but doesn't touch the ground.Complete the step by standing up and repeat the movement with the other leg.")
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-19, 6)
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-19, 8)

insert into exercise(id, name, equipment, exercise_type, description) values (-20, "Arnold shoulder press ", 4, 0, "Seat on a flat surface. Hold a single dumbbell in front rack position with palms facing towards yourself. As you lift the dumbbell above your head twist it so that at the top your palm faces away from you. Twist again when descending the dumbbell to starting position.")
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-20, 0)

insert into exercise(id, name, equipment, exercise_type, description) values (-21, "Lateral raises", 4, 0, "Stand straight holding two dumbbells at your side. Keeping your core engaged, back straight and elbows locked out raised the dumbbells at your sides to face level. Hold for a second and descend to starting position.")
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-21, 0)

insert into exercise(id, name, equipment, exercise_type, description) values (-22, "Bench press", 4, 0, "The movement is very similar to bench pressing with a barbell, however, the weight is brought down to the chest at a lower point. Hold two dumbbells and lay down on a bench. Hold the weights next to the chest, at the height of your nipples and press them up till the arms are stretched. Let the weight slowly and controlled down")
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-22, 3)

insert into exercise(id, name, equipment, exercise_type, description) values (-23, "Bicep curl", 4, 0, "Hold two dumbbells, the arms are streched, the hands are on your side, the palms face inwards. Bend the arms and bring the weight with a fast movement up. At the same time, rotate your arms by 90 degrees at the very beginning of the movement. At the highest point, rotate a little the weights further outwards. Without a pause, bring them down, slowly.")
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-23, 2)

insert into exercise(id, name, equipment, exercise_type, description) values (-24, "Bent over rows", 4, 0, "With dumbbells in hand, bend at the hip until hands hang just below the knees (similar to straight-legged-deadlift starting position). Keep upper body angle constant while contracting your lats to pull you ellbows back pinching the shoulder blades at the top. Try not to stand up with every rep, check hands go below knees on every rep.")
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-24, 4)

insert into exercise(id, name, equipment, exercise_type, description) values (-25, "Farmers walk", 4, 0, "Stand straight holding two dumbbells at your side. Keeping your core engaged, back straight and shoulder blades retracted start walking.")
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-25, 4)
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-25, 1)

insert into exercise(id, name, equipment, exercise_type, description) values (-29, "Kickbacks", 4, 0, "Hold a dumbbell in each hand with your palms facing in toward each other, keeping your knees bent slightly. Engage your core and maintain a straight spine as you hinge forward at the waist, bringing your torso almost parallel to the floor. Keep your upper arms in close to your body and your head in line with your spine, tucking your chin in slightly. On an exhale, engage your triceps by straightening your elbows. Hold your upper arms still, only moving your forearms during this movement. Pause here, then inhale to return the weights to the starting position.")
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-29, 1)

----- DIP BAR EXERCISES -----
insert into exercise(id, name, equipment, exercise_type, description) values (-27, "Dips", 3, 0, "Hold onto the bars at a narrow place (if they are not parallel) and press yourself up, but don't stretch the arms completely, so the muscles stay during the whole exercise under tension. Now bend the arms and go down as much as you can, keeping the elbows always pointing back, At this point, you can make a short pause before repeating the movement.")
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-27, 1)
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-27, 3)


----- NONE EQUIPMENT EXERCISES -----
insert into exercise(id, name, equipment, exercise_type, description) values (-26, "Burpees", 0, 1, "From standing position do a squat, at the bottom of the squat drop and do a push up, jump back to squat position, extend your legs and jump. Repeat.")
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-26, 1)
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-26, 3)
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-26, 6)

insert into exercise(id, name, equipment, exercise_type, description) values (-28, "Push ups", 0, 2, "Start with your body streched, your hands are shoulder-wide appart on the ground. Push yourself off the ground till you strech your arms. The back is always straight and as well as the neck (always look to the ground). Lower yourself to the initial position and repeat.")
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-28, 3)
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-28, 1)

insert into exercise(id, name, equipment, exercise_type, description) values (-30, "Bear walk", 0, 2, "Rest your weight on your palms and the balls of your feet, not dissimilar to normal pushup position. Move by stepping with your R palm and L foot, then your L palm and R foot.  Basically, walk like a lumbering bear.")
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-30, 0)
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-30, 3)
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-30, 5)

insert into exercise(id, name, equipment, exercise_type, description) values (-31, "Jumping jacks", 0, 1, "Stand with feet together and arms at the sides. Jump to a position with the legs spread wide and the hands touching overhead. Repeat.")
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-31, 0)
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-31, 9)
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-31, 5)

insert into exercise(id, name, equipment, exercise_type, description) values (-32, "Jumping squats", 0, 1, "Stand with feet shoulder width and knees slightly bent. Bend your knees and descend to a full squat position. At the bottom of the squat, powerfully explode straight up bringing your knees toward your chest while in midair. At the top of the jump, your thighs should touch your torso. Release your legs, control your landing by going through your foot (toes, ball, arches, heel) and descend into the squat again for another explosive jump. Upon landing immediately repeat the next jump.")
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-32, 6)
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-32, 7)
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-32, 8)
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-32, 9)

insert into exercise(id, name, equipment, exercise_type, description) values (-33, "Mountain climbers", 0, 1, "Get into a plank position, making sure to distribute your weight evenly between your hands and your toes. Check your form—your hands should be about shoulder-width apart, back flat, abs engaged, and head in alignment. Pull your right knee into your chest as far as you can. Then switch, pulling that knee out and bringing the other knee in. Keeping your hips down, run your knees in and out as far and as fast as you can. Alternate inhaling and exhaling with each leg change.")
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-33, 1)
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-33, 5)
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-33, 6)

insert into exercise(id, name, equipment, exercise_type, description) values (-34, "Calf raises", 0, 0, "Stand on the edge of a step or elevated surface. Stand tall with your abdominals pulled in, the balls of your feet firmly planted on the step, and your heels hanging over the edge. Raise your heels a few inches above the edge of the step so that you’re on your tiptoes. Hold the position for a moment, and then lower your heels below the platform, feeling a stretch in your calf muscles.")
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-34, 9)

insert into exercise(id, name, equipment, exercise_type, description) values (-35, "Single leg calf raises", 0, 0, "Stand on the edge of a step or elevated surface. Stand tall with your abdominals pulled in, with one foot planted on the step, and your heels hanging over the edge. Raise your heel a few inches above the edge of the step so that you’re on your tiptoes. Hold the position for a moment, and then lower your heel below the platform, feeling a stretch in your calf muscles.")
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-35, 9)

-- Jump rope exercises --
insert into exercise(id, name, equipment, exercise_type, description) values (-36, "Single unders", 6, 1, "Jump over the rope with both feet slightly apart. Keep your core tight, back straight, legs slightly bent. Jump using both feet and land on your toes.")
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-36, 0)
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-36, 5)
insert into exercise_targeted_muscles(exercise_id, targeted_muscles) values (-36, 9)


