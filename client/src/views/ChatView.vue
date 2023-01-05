<template>
  <div className="chatpage">
    <vue-advanced-chat
      height="calc(89vh - 20px)"
      :current-user-id="JSON.stringify(currentUserId)"
      :room-id="currentroomId"
      :rooms="JSON.stringify(rooms)"
      :rooms-loaded="true"
      :messages="JSON.stringify(messages)"
      :messages-loaded="messagesLoaded"
      @send-message="sendMessage($event.detail[0])"
      @fetch-messages="fetchMessages($event.detail[0])"
    />
  </div>
</template>

<script>
import axios from "axios";
import { apiUrl } from "@/services/ApiService";
import { register } from "vue-advanced-chat";
register();

export default {
  data() {
    return {
      currentUserId: 321,
      otherUserId: null,
      currentroomId: "",
      user: {
        username: "",
        uid: null,
      },
      rooms: [
        {
          roomId: "1",
          roomName: "Requests",
          avatar:
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSiku6Azkd4oJ7LTzv4z6wzXJj3rvcGXBvxiw&usqp=CAU",
          users: [{ _id: "1234", username: "John Doe" }],
        },
      ],
      roomList: [],
      messages: [],
      messagesLoaded: false,
    };
  },
  // computed: {
  //   loadedRooms() {
  //     return this.rooms.slice(0, this.roomsLoadedCount);
  //   },
  // },
  mounted() {
    if (sessionStorage.getItem("userInfo")) {
      // store user info into sessionStorage
      let temp = JSON.parse(sessionStorage.getItem("userInfo"));
      this.user.uid = temp["id"];
      this.user.username = temp["username"];
      this.currentUserId = this.user.uid;
    }
    // get id and name from post detail, then check room
    var urlParam = decodeURI(window.location.href.split("?")[1]);
    console.log(urlParam);
    if (urlParam.indexOf("&") != -1) {
      var driver_id = urlParam.split("pid=")[1].split("&")[0];
      this.otherUserId = driver_id;
      console.log(driver_id);
      var driver_name = urlParam.split("pid=")[1].split("&")[1];
      this.creatRoom(driver_id, driver_name.split("pname=")[1], this.user);
    }
    this.getRoomList();
  },
  methods: {
    creatRoom(driver, drivername, passenger) {
      let data = {
        id: null,
        roomname: drivername + "&" + passenger.username,
        user1Id: driver,
        user1Name: drivername,
        user2Id: passenger.uid,
        user2Name: passenger.username,
      };
      const url = "room/";
      var start_new_room = {};
      axios.post(url, data).then((res) => {
        data = res.data;
        start_new_room = {
          roomId: String(data.id),
          roomName: String(data.roomname),
          avatar:
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLyzHVYXjnkLAZToL5m9nMb3rlUHl2Z1P7PQ&usqp=CAU",
          users: [
            {
              _id: String(data.user1Id),
              username: String(data.user1Name),
            },
            {
              _id: String(data.user2Id),
              username: String(data.user2Name),
            },
          ],
        };
        this.rooms.push(start_new_room);
        console.log(data.id);
        this.currentroomId = data.id;
      });
    },
    getRoomList() {
      axios.get(apiUrl + "room/" + this.currentUserId).then((res) => {
        this.roomList = res.data;
        this.roomList.forEach((item) => {
          var room = {
            roomId: String(item.id),
            roomName: item.roomname,
            avatar:
              "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLyzHVYXjnkLAZToL5m9nMb3rlUHl2Z1P7PQ&usqp=CAU",
            users: [
              { _id: String(item.user1_id), username: String(item.user1_name) },
              { _id: String(item.user2_id), username: String(item.user1_name) },
            ],
          };
          this.rooms.push(room);
        });
      });
    },
    fetchMessages({ room, options = {} }) {
      this.selectedRoom = room.roomId;
      if (options.reset) {
        this.messages = [];
        this.addMessages(this.selectedRoom);
      } else {
        this.messages = [...this.addMessages(), ...this.messages];
        this.messagesLoaded = true;
      }
    },

    addMessages(roomId) {
      axios.get(apiUrl + "message/" + parseInt(roomId)).then((res) => {
        var data = res.data;
        data.forEach((message) => {
          this.messages = [
            ...this.messages,
            {
              _id: message.id,
              content: String(message.content),
              senderId: String(message.senderId),
              username: "John Doe",
              date: new Date(message.sendTime).toISOString().substring(0, 10),
              timestamp: new Date(message.sendTime)
                .toISOString()
                .substring(11, 16),
            },
          ];
        });
      });
      //when start a new chat, add hello message
      if (this.messages.length == 0) {
        this.addHelloMessage();
      }
    },

    sendMessage(message) {
      const data = {
        _id: this.messages.length,
        content: message.content,
        senderId: String(this.currentUserId),
        timestamp: new Date().toISOString().substring(11, 16),
        date: new Date().toISOString().substring(0, 10),
      };
      this.messages.push(data);
      console.log(this.selectedRoom);
      const new_data = {
        id: null,
        roomId: this.currentroomId,
        senderId: this.currentUserId,
        content: message.content,
        sendTime: new Date()
          .toISOString()
          .replace(/T/g, " ")
          .replace(/\.[\d]{3}Z/, ""),
        deleted: 0,
      };
      const url = "message/";
      axios.post(url, new_data);
    },

    addHelloMessage() {
      this.messages = [
        ...this.messages,
        {
          _id: this.messages.length,
          content: "Hi! Let's start chat~",
          senderId: "1234567",
          timestamp: new Date().toISOString().substring(11, 16),
          date: new Date().toISOString().substring(0, 10),
        },
      ];
      // setTimeout(() => {}, 20);
    },
  },
};
</script>
